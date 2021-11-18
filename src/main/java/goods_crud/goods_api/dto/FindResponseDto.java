package goods_crud.goods_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class FindResponseDto {

	@lombok.Getter
	@lombok.Setter
	@ToString
	@JsonInclude(JsonInclude.Include.NON_ABSENT)
	public static class GoodsResponse {
		private List<FindResultGoodsDto> findResultGoodsDtoList;
		private Integer count;
		private FindResultGoodsDto findResultGoodsDto;

		public GoodsResponse toDoList(List<FindResultGoodsDto> findResultGoodsDtoList){
			this.findResultGoodsDtoList = findResultGoodsDtoList;
			this.count = findResultGoodsDtoList.size();
			return this;
		}

		public GoodsResponse toDo(FindResultGoodsDto findResultGoodsDto){
			this.findResultGoodsDto = findResultGoodsDto;
			return this;
		}
	}
}
