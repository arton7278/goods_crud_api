package goods_crud.goods_api.common;

public enum ErrorCode {
    //검색
    DATA_NOT_GOODS("S0001", "데이터가 존재하지 않습니다."),
    //유효성 체크
    NOT_EXIST_GOODS_NAME("V0001", "상품명은 필수 입니다."),
    NOT_EXIST_GOODS_PRICE("V0002", "상품가격은 필수 입니다."),
    NOT_EXIST_GOODS_COM_ID("V0003", "상품의 업체정보는 필수 입니다."),
    NOT_EXIST_GOODS_GENDER_TYPE("V0004", "상품의 설명은 필수 입니다.");

    private final String code;
    private final String description;

    private ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
