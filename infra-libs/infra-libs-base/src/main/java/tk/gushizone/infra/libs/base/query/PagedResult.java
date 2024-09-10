package tk.gushizone.infra.libs.base.query;

import lombok.Data;

import java.util.List;

@Data
public class PagedResult<T> {

    private Paged page;

    private List<T> records;

}
