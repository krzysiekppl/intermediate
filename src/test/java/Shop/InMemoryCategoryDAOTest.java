package Shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class InMemoryCategoryDAOTest {

    @Test
    void shouldPopulateParentCategoryIds() {
        List<Category> list= InMemoryCategoryDAO.getInstance().categoryListResult;

        Assertions.assertEquals(Integer.valueOf(4),list.stream().filter(s->s.getId().equals(5)).findFirst().get().getParentId());
    }
}