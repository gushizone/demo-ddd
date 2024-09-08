package tk.gushizone.mall.stock.domain.model.entity;

import lombok.Data;

/**
 * 库存
 */
@Data
public class StockEntity {

    private Long id;

    private Long productId;

    private Integer quantity;
}