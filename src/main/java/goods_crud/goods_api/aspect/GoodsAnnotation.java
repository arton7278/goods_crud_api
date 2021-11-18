package goods_crud.goods_api.aspect;

import java.lang.annotation.*;

public class GoodsAnnotation {

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface MethodRunTimeCheck  {
        String name() default "";
    }

}
