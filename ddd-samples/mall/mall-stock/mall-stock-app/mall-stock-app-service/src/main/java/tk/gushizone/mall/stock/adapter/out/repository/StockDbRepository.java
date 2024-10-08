package tk.gushizone.mall.stock.adapter.out.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import tk.gushizone.infra.libs.base.entity.query.PageableResult;
import tk.gushizone.infra.libs.base.entity.query.PageableParam;
import tk.gushizone.infra.libs.core.mybatisplus.Pages;
import tk.gushizone.mall.stock.adapter.out.repository.assembler.StockRepositoryAssembler;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;
import tk.gushizone.mall.stock.domain.repository.StockRepository;
import tk.gushizone.mall.stock.infrastructure.repository.db.mapper.StockMapper;
import tk.gushizone.mall.stock.infrastructure.repository.db.po.Stock;

import java.util.List;

@Repository
public class StockDbRepository implements StockRepository {

    @Resource
    private StockMapper stockMapper;

    @Override
    public PageableResult<StockAggregate> query(PageableParam<StockQry> pageableParam) {

        StockQry stockQry = pageableParam.getParam();

        Page<Stock> stockPage = stockMapper.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(stockQry.getProductIds()), Stock::getProductId, stockQry.getProductIds())
                .page(Pages.toPage(pageableParam.getPage()));
        if (CollectionUtils.isEmpty(stockPage.getRecords())) {
            return Pages.toResult(stockPage);
        }
        List<StockAggregate> stocks = StockRepositoryAssembler.toAgg(stockPage.getRecords());
        return Pages.toResult(stockPage, stocks);
    }

    @Override
    public void save(StockAggregate stock) {

    }
}
