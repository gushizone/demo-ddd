package tk.gushizone.infra.libs.base.entity.query;

public interface OrderEntry {

    String COLUMN_ID = "id";

    String ORDER_AES = "asc";
    String ORDER_DESC = "desc";

    String getColumn();

    void setColumn(String column);

    String getOrder();

    void setOrder(String order);
}
