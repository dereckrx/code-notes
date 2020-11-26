package javaNotes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.With;
import lombok.val;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Lom {
  @Value
  @With
  @Accessors(fluent = true)
  static class ImmutableVal {
    Integer value;
  }

  @Data
  static class SettableData {
    Integer value;
  }

  @Value
  @Builder
  static class Buildable {
    Integer value;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  static class Entity {
    Integer value;
  }

  @SneakyThrows
  public static void checkedToRuntime(boolean shouldThrow) {
    if(shouldThrow) {
      throw new Exception();
    }
  }
}

public class LombokTest {

  @Test
  void foo() {
    val value = new Lom.ImmutableVal(1);
    assertEquals(value.getValue(), 1);

    val value2 = value.withValue(2);
    assertEquals(value2.getValue(), 2);

    val data = new Lom.SettableData();
    assertEquals(data.getValue(), null);
    data.setValue(1);

    val build1 = Lom.Buildable.builder().value(1).build();
    val build2 = new Lom.Buildable(1);
    assert(!(build1 == build2));
    assertEquals(build1, build2);

    val entity1 = new Lom.Entity();
    entity1.value = 1;
    Lom.Entity entity2 = new Lom.Entity(1);
    val entity3 = Lom.Entity.builder().value(1).build();
    assertEquals(entity1, entity2);
    assertEquals(entity2, entity3);

    assertDoesNotThrow(() -> {
      Lom.checkedToRuntime(false);
    });
  }
}
