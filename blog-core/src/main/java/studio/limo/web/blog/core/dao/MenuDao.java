package studio.limo.web.blog.core.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import studio.limo.web.blog.core.bean.Menu;

public interface MenuDao extends PagingAndSortingRepository<Menu, Long>, QueryByExampleExecutor<Menu> {
}
