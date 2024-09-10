package tk.gushizone.infra.libs.base.query;

public interface OrderEntry {

    static final String ORDER_AES = "asc";
    static final String ORDER_DESC = "desc";

    String getColumn();

    void setColumn(String column);

    String getOrder();

    void setOrder(String order);
}
