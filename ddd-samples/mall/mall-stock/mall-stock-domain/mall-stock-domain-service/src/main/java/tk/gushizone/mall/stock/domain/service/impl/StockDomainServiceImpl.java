package tk.gushizone.mall.stock.domain.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import tk.gushizone.infra.libs.base.entity.query.PageableResult;
import tk.gushizone.infra.libs.base.entity.query.PageableParam;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;
import tk.gushizone.mall.stock.domain.repository.StockRepository;
import tk.gushizone.mall.stock.domain.service.StockDomainService;

/**
 * @author gushizone
 * @since 2022/10/18 17:47
 */
@Service
public class StockDomainServiceImpl implements StockDomainService {

    @Resource
    private StockRepository stockRepository;

    @Override
    public PageableResult<StockAggregate> query(PageableParam<StockQry> pageableParam) {
        return stockRepository.query(pageableParam);
    }
}
