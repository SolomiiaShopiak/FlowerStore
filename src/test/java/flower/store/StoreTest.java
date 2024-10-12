package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class StoreTest {

    private Store store;

    @BeforeEach
    public void setUp() {
        Flower rose = new Rose();
        rose.setPrice(10);
        rose.setColor(FlowerColor.RED);
        FlowerPack rosePack = new FlowerPack(rose, 5);

        Flower chamomile = new Chamomile();
        chamomile.setPrice(5);
        chamomile.setColor(FlowerColor.BLUE);
        FlowerPack chamomilePack = new FlowerPack(chamomile, 10);

        Flower tulip = new Tulip();
        tulip.setPrice(7);
        tulip.setColor(FlowerColor.RED);
        FlowerPack tulipPack = new FlowerPack(tulip, 8);

        FlowerBucket bucket1 = new FlowerBucket();
        bucket1.add(rosePack);
        bucket1.add(chamomilePack);

        FlowerBucket bucket2 = new FlowerBucket();
        bucket2.add(tulipPack);

        List<FlowerBucket> buckets = new ArrayList<>();
        buckets.add(bucket1);
        buckets.add(bucket2);

        store = new Store(buckets);
    }

    @Test
    public void testSearchByType() {
        List<FlowerPack> result = store.search(FlowerType.ROSE, null, null);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(FlowerType.ROSE, result.get(0).getFlower().getFlowerType());
    }

    @Test
    public void testSearchByColor() {
        List<FlowerPack> result = store.search(null, FlowerColor.RED, null);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack -> pack.getFlower().getColor().equals(FlowerColor.RED.toString())));
    }

    @Test
    public void testSearchByTypeAndColor() {
        List<FlowerPack> result = store.search(FlowerType.TULIP, FlowerColor.RED, null);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(FlowerType.TULIP, result.get(0).getFlower().getFlowerType());
        Assertions.assertEquals(FlowerColor.RED.toString(), result.get(0).getFlower().getColor());
    }

    @Test
    public void testSearchByTypeAndPrice() {
        List<FlowerPack> result = store.search(FlowerType.CHAMOMILE, null, 5.0);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(FlowerType.CHAMOMILE, result.get(0).getFlower().getFlowerType());
        Assertions.assertTrue(result.get(0).getFlower().getPrice() <= 5.0);
    }

    @Test
    public void testSearchByColorAndPrice() {
        List<FlowerPack> result = store.search(null, FlowerColor.RED, 10.0);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack -> pack.getFlower().getColor().equals(FlowerColor.RED.toString())));
        Assertions.assertTrue(result.stream().allMatch(pack -> pack.getFlower().getPrice() <= 10.0));
    }

    @Test
    public void testSearchByAllAttributes() {
        List<FlowerPack> result = store.search(FlowerType.ROSE, FlowerColor.RED, 10.0);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(FlowerType.ROSE, result.get(0).getFlower().getFlowerType());
        Assertions.assertEquals(FlowerColor.RED.toString(), result.get(0).getFlower().getColor());
        Assertions.assertTrue(result.get(0).getFlower().getPrice() <= 10.0);
    }

    @Test
    public void testSearchNoMatch() {
        List<FlowerPack> result = store.search(FlowerType.TULIP, FlowerColor.BLUE, null);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void testSearchEmptyStore() {
        Store emptyStore = new Store(new ArrayList<>());
        List<FlowerPack> result = emptyStore.search(null, null, null);
        Assertions.assertEquals(0, result.size());
    }
}
