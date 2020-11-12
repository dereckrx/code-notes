import { PostToSlack, GetEnvSlackChannel } from '../../../../util/slack';

jest.mock('../../../../util/slack');
;
const mockPostToSlack = PostToSlack as jest.Mock;


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

// const mockGetCompanyRepo = mocked(repositories.GetCompanyRepo, true);

Mock interface with `jest-mock-extended`
const mock = mock<SomeInterface>();
// typechecked mock with 

// const mockCompanyRepo = jest.genMockFromModule('./hello');

// === Ways to mock with type saftey === (Best to wors)
// const mockCompanyRepo = mocked(getRepository(Company));
// const mockGetCompanyRepo = GetCompanyRepo as jest.Mock<Repository<Company>>;
// const mockGetCompanyRepo = <jest.Mock<Repository<Company>>>GetCompanyRepo;

Example of mocking typeorm
https://medium.com/better-programming/typescript-jest-testing-challenges-c010eaa8f3f2