package io.github.yaowenbin.commons.bean;

import io.github.yaowenbin.commons.Person;
import io.github.yaowenbin.commons.UnitTest;
import io.github.yaowenbin.commons.map.Maps;
import org.junit.jupiter.api.Disabled;
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
        BeanTestBean source = mockBean();

        BeanTestBeanDTO target = new BeanTestBeanDTO();
        BeanTestBeanDTO result = Beans.copyProperties(source, target);

        assertPropertiesEquals(result, source);

    }

    @Test
    void copyProperties_targetIsClass() {
        BeanTestBean source = mockBean();

        BeanTestBeanDTO result = Beans.copyProperties(source, BeanTestBeanDTO.class);

        assertThat(result.getCreateTime()).isEqualTo(source.getCreateTime());
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("beanTest");
    }


    @Test
    void toMap() {


    }


    @Test
    @Disabled
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

    private BeanTestBean mockBean() {
        BeanTestBean bean = new BeanTestBean().setName("beanTest").createTime(new Date());
        bean.setId(1L);
        return bean;
    }

    private void assertPropertiesEquals(BeanTestBeanDTO result, BeanTestBean source) {
        assertThat(result.getCreateTime()).isEqualTo(source.getCreateTime());
        assertThat(result.id()).isEqualTo(source.id());
        assertThat(result.getName()).isEqualTo(source.getName());
    }
}

class BeanTestBean {

    private Long id;
    public String name;
    private Date createTime;

    // record style.
    public Long id() {
        return id;
    }

    // default style
    public String getName() {
        return name;
    }

    // default style.
    public Date getCreateTime() {
        return createTime;
    }

    // default style
    public void setId(Long id) {
        this.id = id;
    }

    // chain style.
    public BeanTestBean setName(String name) {
        this.name = name;
        return this;
    }

    // fluent style.
    public BeanTestBean createTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}

class BeanTestBeanDTO {

    private Long id;
    public String name;
    private Date createTime;

    // record style.
    public Long id() {
        return id;
    }

    // default style
    public String getName() {
        return name;
    }

    // default style.
    public Date getCreateTime() {
        return createTime;
    }

    // default style
    public void setId(Long id) {
        this.id = id;
    }

    // chain style.
    public BeanTestBeanDTO setName(String name) {
        this.name = name;
        return this;
    }

    // fluent style.
    public BeanTestBeanDTO createTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}