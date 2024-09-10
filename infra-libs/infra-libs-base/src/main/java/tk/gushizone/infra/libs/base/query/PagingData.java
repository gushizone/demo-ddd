package tk.gushizone.infra.libs.base.query;

import java.util.List;

/**
 * @author gushizone
 * @since 2023/6/1
 */
public interface PagingData {

    long getCurrent();

    void setCurrent(long current);

    long getSize();

    void setSize(long size);

    List<? extends OrderEntry> getOrders();

    void setOrders(List<? extends OrderEntry> orders);
}