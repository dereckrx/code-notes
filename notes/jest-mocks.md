---
title: unknown
---

import { PostToSlack, GetEnvSlackChannel } from '../../../../util/slack';

jest.mock('../../../../util/slack');
;
const mockPostToSlack = PostToSlack as jest.Mock;

## Three ways
All require jest.mock('../path/to/file');
jest.mock('../fooService);
import * as FooService from '../fooService);

const mockFooService = mock(FooService); // 
const mockFooService = <jest.Mock<IFooService>>FooService;
const mockFooServiceInstance = mocked(new FooService()); mys

## -----
https://stackoverflow.com/questions/48759035/mock-dependency-in-jest-with-typescript
```
import { SomeClass } from './SomeClass';

jest.mock('./SomeClass');

const mockedClass = <jest.Mock<SomeClass>>SomeClass;
// or
const mockedExistsSync = <jest.Mock<typeof SomeClass>>SomeClass;
```

const typedMockPredicate: jest.Mock<ReturnType<Predicate>> = jest.fn();

// const mockGetCompanyRepo = jest.spyOn(repositories, 'GetCompanyRepo');
// const mockGetCompanyRepo = <jest.Mock<Repository<Company>>>(
//   repositories.GetCompanyRepo
// );

// const Mock = jest.fn<ICommunicator<IEmail>>(() => ({
//   send: jest.fn(),
// }));

## Using mocked from ts-jest
Takes an instance
// const mockGetCompanyRepo = mocked(repositories.GetCompanyRepo, true);

## Mock interface 
with `jest-mock-extended`
const mock = mock<SomeInterface>();
// typechecked mock with 

// const mockCompanyRepo = jest.genMockFromModule('./hello');

// === Ways to mock with type saftey === (Best to wors)
// const mockCompanyRepo = mocked(getRepository(Company));
// const mockGetCompanyRepo = GetCompanyRepo as jest.Mock<Repository<Company>>;
// const mockGetCompanyRepo = <jest.Mock<Repository<Company>>>GetCompanyRepo;

Example of mocking typeorm
https://medium.com/better-programming/typescript-jest-testing-challenges-c010eaa8f3f2
