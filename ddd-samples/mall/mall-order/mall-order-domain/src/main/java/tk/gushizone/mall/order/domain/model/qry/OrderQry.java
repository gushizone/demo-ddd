package tk.gushizone.mall.order.domain.model.qry;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderQry{

    private List<Long> ids;
}
