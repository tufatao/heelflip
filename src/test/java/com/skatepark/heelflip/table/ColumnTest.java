package com.skatepark.heelflip.table;

import com.google.gson.JsonPrimitive;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class ColumnTest {

    @Test
    public void testDoubleValues() {

        ObjectColumn column = new ObjectColumn("a");
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive(2.3)));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive(2.1)));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive(2.0)));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive(2.0)));

        Assert.assertEquals("a", column.name());

        ColumnStatistic statistics = column.getStatistics();
        Assert.assertEquals(4, statistics.getCount());
        Assert.assertEquals(0, statistics.getStringCount());
        Assert.assertEquals(0, statistics.getBooleanCount());
        Assert.assertEquals(0, statistics.getLongCount());
        Assert.assertEquals(4, statistics.getDoubleCount());
        Assert.assertEquals(2.0, statistics.getMin().doubleValue(), 10E10);
        Assert.assertEquals(2.3, statistics.getMax().doubleValue(), 10E10);
        Assert.assertEquals(8.4, statistics.getSum().doubleValue(), 10E10);

        Assert.assertEquals(4, column.count());
        Assert.assertEquals(2, column.count(2.0));
        Assert.assertEquals(0, column.count(1.0));

        Assert.assertEquals(1, column.valuesAsIntSet().size());
        Assert.assertEquals(1, column.valuesAsLongSet().size());
        Assert.assertEquals(3, column.valuesAsDoubleSet().size());
        Assert.assertEquals(3, column.valuesAsStringSet().size());
    }

    @Test
    public void testLongValues() {
        ObjectColumn column = new ObjectColumn("a");
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive(10)));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive(11)));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive(12)));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive(12)));

        Assert.assertEquals("a", column.name());

        ColumnStatistic statistics = column.getStatistics();
        Assert.assertEquals(4, statistics.getCount());
        Assert.assertEquals(0, statistics.getStringCount());
        Assert.assertEquals(0, statistics.getBooleanCount());
        Assert.assertEquals(0, statistics.getLongCount());
        Assert.assertEquals(4, statistics.getDoubleCount());
        Assert.assertEquals(10, statistics.getMin().intValue());
        Assert.assertEquals(12, statistics.getMax().intValue());
        Assert.assertEquals(45, statistics.getSum().intValue());

        Assert.assertEquals(4, column.count());
        Assert.assertEquals(2, column.count(12));
        Assert.assertEquals(0, column.count(13));

        Assert.assertEquals(3, column.valuesAsIntSet().size());
        Assert.assertEquals(3, column.valuesAsLongSet().size());
        Assert.assertEquals(3, column.valuesAsDoubleSet().size());
        Assert.assertEquals(3, column.valuesAsStringSet().size());
    }

    @Test
    public void testStringColumn() {
        ObjectColumn column = new ObjectColumn("a");
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive("foo")));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive("foo")));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive("boo")));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive("call")));

        Assert.assertEquals("a", column.name());

        ColumnStatistic statistics = column.getStatistics();
        Assert.assertEquals(4, statistics.getCount());
        Assert.assertEquals(0, statistics.getStringCount());
        Assert.assertEquals(0, statistics.getBooleanCount());
        Assert.assertEquals(0, statistics.getLongCount());
        Assert.assertEquals(4, statistics.getDoubleCount());
        Assert.assertEquals(0, statistics.getMin().intValue());
        Assert.assertEquals(0, statistics.getMax().intValue());
        Assert.assertEquals(0, statistics.getSum().intValue());

        Assert.assertEquals(4, column.count());
        Assert.assertEquals(2, column.count("foo"));
        Assert.assertEquals(0, column.count("other"));

        Assert.assertEquals(0, column.valuesAsIntSet().size());
        Assert.assertEquals(0, column.valuesAsLongSet().size());
        Assert.assertEquals(0, column.valuesAsDoubleSet().size());
        Assert.assertEquals(3, column.valuesAsStringSet().size());
    }

    @Test
    public void testMixedColumn() {
        ObjectColumn column = new ObjectColumn("a");
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive("foo")));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive("true")));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive("1.2")));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive("10")));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive("20")));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive(1.2)));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive(10)));
        column.add(new HFValue("a", UUID.randomUUID(), new JsonPrimitive(15L)));

        Assert.assertEquals("a", column.name());

        ColumnStatistic statistics = column.getStatistics();
        Assert.assertEquals(4, statistics.getCount());
        Assert.assertEquals(0, statistics.getStringCount());
        Assert.assertEquals(0, statistics.getBooleanCount());
        Assert.assertEquals(0, statistics.getLongCount());
        Assert.assertEquals(4, statistics.getDoubleCount());
        Assert.assertEquals(10, statistics.getMin().intValue());
        Assert.assertEquals(12, statistics.getMax().intValue());
        Assert.assertEquals(45, statistics.getSum().intValue());

        Assert.assertEquals(8, column.count());
        Assert.assertEquals(2, column.count(10));
        Assert.assertEquals(0, column.count(13));

        Assert.assertEquals(4, column.valuesAsIntSet().size());
        Assert.assertEquals(4, column.valuesAsLongSet().size());
        Assert.assertEquals(4, column.valuesAsDoubleSet().size());
        Assert.assertEquals(6, column.valuesAsStringSet().size());
    }
}
