// Mocking in Jest

import Object from "./object" // If it is a class that is exported
import * as Module from "./module" // If it is exported functions

jest.spy(Object, "methodName").mockReturnValue(response);

const promis = jest.fn().mockResolvedValue("result");
