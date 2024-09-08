package tk.gushizone.mall.stock.dto.rsp;

import lombok.Data;

@Data
public class StockApiRsp {

    private Long id;

    private Long productId;

    private Integer quantity;
}
