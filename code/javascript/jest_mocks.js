// Todo:
// * allow and expect mocks


// Mocking in Jest

import Object from "./object" // If it is a class that is exported
import * as Module from "./module" // If it is exported functions


jest.spy(Object, "methodName").mockReturnValue(response);

const promis = jest.fn().mockResolvedValue("result");

const myMock = jest.fn();

myMock.mockReturnValueOnce(10).mockReturnValueOnce('x').mockReturnValue(true);

console.log(myMock(), myMock(), myMock(), myMock());
// > 10, 'x', true, true


// Must be imported:
// import axios from 'axios';
jest.mock('axios');

test('should fetch users', () => {
  const resp = {data: [{name: 'Bob'}]};
  axios.get.mockResolvedValue(resp);
  axios.get().then(resp => expect(resp).toEqual(resp));
});

// Mock an interface with jest-mock-extended
const mockedAnvilClient = mock<AnvilClientInterface>();

// Mocking a constructor 
import Anvil from '@anvilco/anvil';
import { mock } from 'jest-mock-extended';
import { AnvilClientInterface } from '../../../../lib/anvilPdfClient';

jest.mock('@anvilco/anvil', () => jest.fn());
const mockedAnvilClient = mock<AnvilClientInterface>();
Anvil.mockImplementation(() => mockedAnvilClient);
mockedAnvilClient.fillPDF.mockResolvedValue({ statusCode: 200, data: [] });
