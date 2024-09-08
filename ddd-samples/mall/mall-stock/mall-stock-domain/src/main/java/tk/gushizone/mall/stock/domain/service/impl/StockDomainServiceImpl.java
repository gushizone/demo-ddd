package tk.gushizone.mall.stock.domain.service.impl;

import org.springframework.stereotype.Service;
import tk.gushizone.mall.stock.domain.model.aggregate.StockAggregate;
import tk.gushizone.mall.stock.domain.model.value.qry.StockQry;
import tk.gushizone.mall.stock.domain.repository.StockRepository;
import tk.gushizone.mall.stock.domain.service.StockDomainService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author gushizone
 * @date 2022/10/18 17:47
 */
@Service
public class StockDomainServiceImpl implements StockDomainService {

    @Resource
    private StockRepository stockRepository;

    @Override
    public List<StockAggregate> query(StockQry stockQry) {
        return stockRepository.query(stockQry);
    }
}
