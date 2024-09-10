package tk.gushizone.mall.stock.adapter.out.repository;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import tk.gushizone.mall.stock.adapter.out.repository.assembler.StockRepositoryAssembler;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;
import tk.gushizone.mall.stock.domain.repository.StockRepository;
import tk.gushizone.mall.stock.infrastructure.repository.db.mapper.StockMapper;
import tk.gushizone.mall.stock.infrastructure.repository.db.po.Stock;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class StockDbRepository implements StockRepository {

    @Resource
    private StockMapper stockMapper;

    @Override
    public void save(StockAggregate stock) {

    }

    @Override
    public List<StockAggregate> query(StockQry qry) {

        List<Stock> stocks = stockMapper.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(qry.getProductIds()), Stock::getProductId, qry.getProductIds())
                .list();

        return StockRepositoryAssembler.toAgg(stocks);
    }
}
