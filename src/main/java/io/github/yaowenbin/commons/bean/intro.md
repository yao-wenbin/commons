Beans

to better operate Any Bean Class.


## toMap()
toConvert Bean to a HashMap.

```java
    @Test
    void toMap() {
        Person person = new Person(1, 2L, "foo");

        Map<String, Object> res = Beans.toMap(person);

        assertThat(res).isInstanceOf(HashMap.class);
        assertThat(res.get("id")).isEqualTo(1);
        assertThat(res.get("no")).isEqualTo(2L);
        assertThat(res.get("username")).isEqualTo("foo");
    }
```

## copyProperties()

to copy a bean's properties to another bean.

```java
    @Test
    void copyProperties_targetIsClass() {
        BeanTestBean source = mockBean();

        BeanTestBeanDTO result = Beans.copyProperties(source, BeanTestBeanDTO.class);

        assertThat(result.getCreateTime()).isEqualTo(source.getCreateTime());
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("beanTest");
    }
```