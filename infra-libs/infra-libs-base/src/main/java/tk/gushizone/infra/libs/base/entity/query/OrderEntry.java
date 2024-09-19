package tk.gushizone.infra.libs.base.entity.query;

/**
 * @author gushizone
 * @since 2024/9/19
 */
public interface OrderEntry {

    String getColumn();

    void setColumn(String column);

    String getOrder();

    void setOrder(String order);
}
