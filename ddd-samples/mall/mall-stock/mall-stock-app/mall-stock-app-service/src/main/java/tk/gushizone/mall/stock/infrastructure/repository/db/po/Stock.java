package tk.gushizone.mall.stock.infrastructure.repository.db.po;

import lombok.Data;

@Data
public class Stock {

    private Long id;

    private Long productId;

    private Integer quantity;
}
