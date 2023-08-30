package io.github.yaowenbin.commons.bean;

import io.github.yaowenbin.commons.Person;
import io.github.yaowenbin.commons.UnitTest;
import io.github.yaowenbin.commons.map.Maps;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

/**
 * @Author yaowenbin
 * @Date 2023/8/28
 */
class BeansTest extends UnitTest {

    @Test
    void copyProperties() {


    }

    @Test
    void toMap() {


    }


    @Test
    void fromMap() {
        Date now = new Date();
        Map<String, Object> fromMap = Maps.<String, Object>builder().put("id", 1).put("no", 123L).put("name", "zhangsan").put("createTime", now).build();

        Person person = Beans.fromMap(fromMap, Person.class);

        assertThat(person.id()).isEqualTo(1);
        assertThat(person.no()).isEqualTo(123L);
        assertThat(person.username()).isEqualTo("zhangsan");
        assertThat(person.createTime()).isEqualTo(now);

        fromMap.put("id", null);
        fromMap.put("createTime", null);

        person = Beans.fromMap(fromMap, Person.class);

        assertThat(person.id()).isEqualTo(null);
        assertThat(person.no()).isEqualTo(123L);
        assertThat(person.username()).isEqualTo("zhangsan");
        assertThat(person.createTime()).isEqualTo((Date)null);

    }
}