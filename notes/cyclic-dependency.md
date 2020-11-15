---
title: unknown
---

## Cyclic Dependency

https://medium.com/angular-in-depth/how-to-break-a-cyclic-dependency-between-es6-modules-fd8ede198596
https://github.com/pragkirk/poma/blob/master/acyclicrelationships/callback/src/com/kirkk/test/PaymentTest.java


A cyclic dependency between two modules

`Customer <--> Order`

```typescript
// order.ts
import {Customer} from './customer';

export function Order(customer: Customer, amount: number) {
  return {
    discountedAmount: () => customer.discount() * amount,
  }
}

// customer.js
import {Order} from './order';

export function Customer() {
  const orders = [];
  return {
    discount: () => orders.length > 5 ? 10 : 3,
    addOrder: (amount) => orders.push(Order(this, amount)),
  }
}

const customer = Customer();
const order1 = customer.addOrder(10);
order1.discountedAmount();
const order2 = Order(customer, 20); // doesn't work, discount will be wrong
```

## Breaking Cyclic Dependency Techniques

* Dependency Inversion (aka. Callback)
* Escalation
* Demotion

### Dependency Inversion 

Create an interface and have both modules depend on that.

`Customer --> [DiscountCalculator <-- Order]`

```typescript
// order.ts
export interface DiscountCalculator { 
  discount(): number; 
}

export interface IOrder {discountedAmount: () => number};

export function Order(discount: DiscountCalculator, amount: number) {
  return {
    discountedAmount: () => discount.discount() * amount,
  }
}

// customer.js
import {Order, DiscountCalculator} from './order';

export function Customer() {
  const orders = [];
  return {
    addOrder: (amount) => {
      const order = Order(this, amount);
      orders.push(order);
      return order;
    },
    discount: () => orders.length > 5 ? 10 : 3,
  }
}
const customer = Customer();
const order1 = customer.addOrder(10);
order1.discountedAmount(); 
const order2 = Order({discount: () => 5}, 20);
order2.discountedAmount();
```

### Escalation

Extract a new module that depends on Customer and Order.

```
   OrderDiscounter 
    |          
    v          
Customer --> Order
                 
``` 

```typescript
// order.ts
export type Order = {amount: number};

// customer.js
import {Order} from './order';

export interface ICustomer {discount: () => number};

export function Customer(orders: Order[]): ICustomer {
  return {
    discount: () => orders.length > 5 ? 10 : 3,
  }
}

// OrderDiscounter.js
import {Order} from './order';
import {ICustomer} from './customer';

export function OrderDiscounter(customer: ICustomer, amount: number) { 
  return customer.discount() * amount;
}

const order1 = Order(10);
const customer = Customer([order1]);
const order1Discount = OrderDiscounter(customer, order1.amount);
const order2 = Order(20);
// Doesn't work without another interface
// const order2Discount = OrderDiscounter({discount: 5}, order2.amount);
```

### Demotion

Extract a new module with no dependencies, and have Customer and Order depend on it.

```
Customer --> Order
  |            | 
  v            v
  OrderDiscounter
```

```typescript
// OrdersDiscount.js
export function OrderDiscount(numOrders: number): {discount: () => number} {
  return {
    discount: () => numOrders > 5 ? 10 : 3
  };
} 

// order.ts
import {OrderDiscount} from './orders';

export function Order(discount: OrderDiscount, amount: number) {
  return {
    discountedAmount: () => discount.discount() * amount,
  }
}

// customer.ts
import {Order} from './order';
import {OrderDiscount} from './orderDiscount';

export function Customer() {
  const orders = [];
  return {
    addOrder: (amount) => {
      const order = Order(OrderDiscount(orders.length + 1), amount);
      orders.push(order);
      return order;
    },
  }
}

// WAT?
const customer = Customer();
const order1 = customer.addOrder(10)
order1.discountedAmount(); 
const order2 = Order(OrderDiscount(2), 20);
order2.discountedAmount(); 
```

### Another option


```
Customer --> Order
  ^             
  |            
  OrderDiscounter
```

```typescript
// order.ts
export interface IOrder {amount: number};

export function Order(amount: number): IOrder {
  return {amount}
}

// customer.ts
import {IOrder} from './order';

export interface ICustomer {orders: IOrder[]};

export function Customer(orders: IOrder[]): ICustomer {
  return {orders}
}

// OrdersDiscount.js
import {ICustomer} from './customer';
import {IOrder} from './order';

export function OrderDiscount(customer: ICustomer, order: IOrder) {
  return {
    discount: () => {
      const discount = customer.orders.length > 5 ? 10 : 3;
      return discount * order.amount;
    }
  };
}

const order1 = Order(10);
const order2 = Order(20);
const customer = Customer([order1, order2]);
const discount = OrderDiscount(customer, order1).discount();
```
