// DOesn't work, but could it to avoid having to cast every string to enum? 'foo' as FooEnum
export const ParseEnum = <T>(enumType: object, value: any): T => {
    if (Object.values(enumType).includes(value)) {
      return value as T;
    }
    throw new HttpException(400, `Invalid ${enumType} '${value}`);
  };
  