package goods_crud.goods_api.common;

public enum ErrorCode {
    DATA_NOT_FOUND(1, "데이터가 존재하지 않습니다."),
    NOT_EXIST_GOODS_NAME(2, "상품명은 필수 입니다."),
    NOT_EXIST_GOODS_PRICE(2, "상품가격은 필수 입니다.");

    private final int code;
    private final String description;

    private ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
