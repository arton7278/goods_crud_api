package goods_crud.goods_api.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import goods_crud.goods_api.dto.FindResultGoodsDto;
import goods_crud.goods_api.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import static goods_crud.goods_api.aspect.GoodsAnnotation.*;

@Slf4j(topic = "SERVER")
@Aspect
@Component
@EnableAsync
@RequiredArgsConstructor
public class GoodsChangeLoggingAspect {

    private final GoodsService goodsService;

    @Around("@annotation(goodsChangeLogging)")
    public Object goodsChangeLogging(final ProceedingJoinPoint joinPoint, final GoodsChangeLogging goodsChangeLogging)throws Throwable {
        //1. goodsNo 정보
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        String[] paramNames = signature.getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        Long goodsNo = null;

        for (int i = 0, l = paramNames.length; i < l; i++) {
            if("goodsNo".equals(paramNames[i])){
                goodsNo = (Long) paramValues[i];
            }
        }

        // 2.[Before]
        FindResultGoodsDto beforeGoodDto = null;
        if(goodsNo != null){
            beforeGoodDto =  goodsService.findGoodsOne(goodsNo);
        }


        //3. process 실행
        Object result = joinPoint.proceed();

        try {
            if(beforeGoodDto != null){
                FindResultGoodsDto afterGoodsDto = goodsService.findGoodsOne(goodsNo);
                FindResultGoodsDto diffGoodsData = (FindResultGoodsDto) findDiffObject(beforeGoodDto, afterGoodsDto, new FindResultGoodsDto());

                log.info("Before Goods Data : {} " , new ObjectMapper().writeValueAsString(beforeGoodDto));
                log.info("After Goods Data : {} ", new ObjectMapper().writeValueAsString(afterGoodsDto));
                log.info("Diff Goods Data : {} ", new ObjectMapper().writeValueAsString(diffGoodsData));

                Map<String, Object> objectMap = PropertyUtils.describe(diffGoodsData);

                for(String key : objectMap.keySet()) {
                    if(objectMap.get(key) != null) {
                        log.info("Goods Change Data =====> {} : {}", key,  objectMap.get(key));
                    }

                }
            }
        } catch (Exception e){
            log.error("logging fail : {} ", e);
            e.printStackTrace();
        }

        return result;
    }


    private Object findDiffObject(Object befObject, Object aftObject, Object obj) {
        Boolean isChanged = false;

        try {
            //현재 접근중이 Class
            for(Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                String fieldName = field.getName();

                Field befField = befObject.getClass().getDeclaredField(fieldName);
                Field aftField = aftObject.getClass().getDeclaredField(fieldName);

                befField.setAccessible(true);
                aftField.setAccessible(true);

                if(compareObject(befField.get(befObject), aftField.get(aftObject))) {
                    field.set(obj, aftField.get(aftObject));
                }
            }

            if(!"Object".equals(obj.getClass().getSuperclass().getSimpleName())){
                for(Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
                    field.setAccessible(true);

                    String fieldName = field.getName();

                    Field befField = befObject.getClass().getDeclaredField(fieldName);
                    Field aftField = aftObject.getClass().getDeclaredField(fieldName);

                    befField.setAccessible(true);
                    aftField.setAccessible(true);

                    if(compareObject(befField.get(befObject), aftField.get(aftObject))) {
                        field.set(obj, aftField.get(aftObject));
                    }
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    private boolean compareObject(Object bef, Object aft) {
        boolean result = false;

        if(Objects.isNull(bef) && Objects.isNull(aft)) {
            result = true;

        }else if(Objects.isNull(bef) && !Objects.isNull(aft)){
            result = false;
        }else {
            if(Objects.equals(bef,aft)) {
                result = true;
            }else {
                result = false;
            }
        }
        return !result;
    }


}
