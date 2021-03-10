## Great notes on game maker
http://orteil.dashnet.org/igm/help.html

http://orteil.dashnet.org/experiments/idlegamemaker/help

## Another great article
https://gamedevelopment.tutsplus.com/articles/numbers-getting-bigger-the-design-and-math-of-incremental-games--cms-24023

1. the presence of at least one currency or number,
2. which increases at a set rate, with no or minimal effort, (clicking)
3. and which can be expended to increase the rate or speed at which it increases

The formula for the cost function here is actually one that's widely used across many incremental games:

Price=BaseCost×Multiplier^(numOwned)

For our Treebeard example, the base cost is 50, and the "Multiplier" variable is 1.07, so the second level costs 50×1.071=53.5, 
the third costs 50×1.072=57.245, and so on. 
The Multiplier's value determines the curvature of the line, with higher values meaning steeper cost curves. (A value of 1 would give a linear cost line.) 

Clicker Heroes uses 1.07 as the increase multiplier for all 35 of its upgradable heroes,
and all the various buildings of Cookie Clicker use a value of 1.15. 
Interestingly, the 10 businesses of AdVenture Capitalist all use a different multiplier, but each is between 1.07 and 1.15. 

Enable and promote a feeling of discovery.
Consider active and inactive play (and ideally reward both).
Don't neglect a unifying theme and art style.
Make use exponential cost scaling, with the most common form being Price=BaseCost×Multiplier(#Owned) with a Multiplier between 1.07 and 1.15.
Provide the player with multiple avenues of optimization.
Extend play through the strategic use of resetting and mechanics that increase complexity.
 
## Optimal Upgrades

NPS = number/resource per second
rate = increase to NPS
(costA/nps) + (costA/(nps+rateA)) 

Imagine a scenario:
* current nps=5 (number per second)
UpgradeA 
    - cost: 20 
    - rate: +1
    - cost per rate: 20
    - 20/5 + 20/5+1 = 4 + 3.33 = 7.33
UpgradeB
    - cost: 100
    - rate: +10 
    - cost per rate: 10
    - 100/5 + 100/5+10 = 20 + 6.66 = 26.66
UpgradeC
    - cost: 40
    - rate: +2
    - cost per rate: 20
    - 40/5 + 40/5+2 = 8 + 5.71 = 13.71
UpgradeD:
    - cost: 10
    - rate: .5
    - cost per rate: 20
    - 10/5 + 10/5.5 = 2 + 1.81 = 3.81 
