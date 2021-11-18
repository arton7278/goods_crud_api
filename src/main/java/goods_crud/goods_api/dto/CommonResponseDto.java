package goods_crud.goods_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CommonResponseDto {
	private Object data;
	private Integer count;

	public CommonResponseDto toDo(Object data){
		this.data = data;
		return this;
	}

	public CommonResponseDto toDoList(List<Object> data){
		this.data = data;
		this.count = data.size();
		return this;
	}
}
