package tk.gushizone.infra.libs.base;

import java.util.List;

/**
 * @author gushizone
 * @since 2023/6/1
 */
public interface IPaging {

    long getCurrent();

    void setCurrent(long current);

    long getSize();

    void setSize(long size);

    List<IOrderItem> getOrders();

    void setOrders(List<IOrderItem> orders);
}