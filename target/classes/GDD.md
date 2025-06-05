# BetterBrewingStands
## Overview


### Mechanics
- Water can be put into the Brewing Stand by right-clicking it with a water bottle. 
- Hoppers can be used to put items into the brewing stand.
- Place Upgrade blocks around brewing stand to increase the brewing speed/level/effect time
- To upgrade an individual effect's level, you need to brew the effect Twice
- All Gui:
  - Water Meter with Input and Output water bottle. Shows max water water upgrade block is near
  - Input Slot for the Ingredient
  - 5 Symbol Slots, Each Symbol Slot Showing Ingredient and the Potion Effect it is making
    - Under each symbol is 2 slots, one for time increase and one for reversal (night vision -> blindness)
  - Fuel Slot for the brewing stand. Only need to supply heat, and therefor any smelting item will work and will work for the normal time

[//]: # (  - Bottle Design Button)

[//]: # (    - Changes the design of the bottle. Should be a collection of buttons that change the design of the bottle)
  - Bottles Storage
    - Where the player puts the bottle
  - Bottle Type Button
    - Changes the type of bottle. Should be a collection of buttons that change the type of bottle
  - Output Slot
    - Where the player gets the potion
- New Potion Brewing Ingredients that give multiple potion effects


[//]: # (### Art needed:)

[//]: # (- Different bottle designs)


### UI design: 
- 5 Potion Effect Slot
  - Each Slot requires an item that corresponds to a potion effect
  - Each Potion Effect Slot has 2 Slots for Potion Effect Upgrades
- Potion Bottle Slot
  - Where the Player puts the bottle

[//]: # (- Bottle Design Slot)

[//]: # (  - Where the Player Chooses the bottle design &#40;If I can get bottle designs working&#41;)
- Water Status Symbol
  - Shows if there is water next to the brewing stand Or in the Brewing Stand

### All Blocks:

- Brewing Stand
  - The brewing stand itself
  - Minecraft Brewing Stand with a new GUI
- Water Upgrade
  - Gives Infinite Water to the stand
  - Minecraft lapis BLock with a new GUI That shows the Water Status
- Level Upgrade
  - Increases the global level of the potion effects
  - minecraft Diamiond Block with a new GUI that shows An arrow pointing up Or an empty slot based on if it is on or no. Turn off by powering
- Time Upgrade 
  - Increases the time of the potion effects
  - Minecraft Gold Block With a new GUI that shows a clock Or an empty slot based on if it is on or no. Turn off by powering

### How:
- for the custom blocks, use conduits with persisten data containers, and check for this data when rigth clicking the block
- for custom blocks, use block states? or i can just use armor stand redering
- 