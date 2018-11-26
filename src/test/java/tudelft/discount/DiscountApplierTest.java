package tudelft.discount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;

import org.mockito.Mockito;


public class DiscountApplierTest {

    private DiscountApplier applier;
    private ProductDao dao;
    private Product p1, p2, p3, p4;

    @BeforeEach
    public void initialize() {
        p1 = new Product("calendar", 10.0, "BUSINESS");
        p2 = new Product("globe", 20.0, "BUSINESS");
        p3 = new Product("map", 1.0, "HOME");
        p4 = new Product("cake", 5.0, "OTHER");
        this.dao = Mockito.mock(ProductDao.class);
        this.applier = new DiscountApplier(dao);
    }


    @Test
    public void empty(){
        Mockito.when(dao.all()).thenReturn(Collections.emptyList());
        applier.setNewPrices();
        Assertions.assertEquals(0, dao.all().size());
        Assertions.assertEquals(10.0, p1.getPrice());
        Assertions.assertEquals(20.0, p2.getPrice());
        Assertions.assertEquals(1.0, p3.getPrice());
        Assertions.assertEquals(5.0, p4.getPrice());
    }


    @Test
    public void simple(){
        Mockito.when(dao.all()).thenReturn(Arrays.asList(p1, p4, p3, p2));
        applier.setNewPrices();
        Assertions.assertEquals(11.0, p1.getPrice());
        Assertions.assertEquals(22.0, p2.getPrice());
        Assertions.assertEquals(0.9, p3.getPrice());
        Assertions.assertEquals(5.0, p4.getPrice());
    }

 }
