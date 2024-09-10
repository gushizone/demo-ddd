package tk.gushizone.infra.libs.base.query;

import java.util.List;

/**
 * @author gushizone
 * @since 2023/6/1
 */
public interface PagedData {

    long getCurrent();

    void setCurrent(long current);

    long getSize();

    void setSize(long size);

    long getTotal();

    void setTotal(long total);

    List<? extends OrderEntry> getOrders();

    void setOrders(List<? extends OrderEntry> orders);
}