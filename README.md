# MarketTime v1.1 by EvanWithaW

A small scheduling plugin for Minecraft Spigot 1.20.6 coded for a friend.
Creates a market that can be accessed only within opening and closing hours

## Features

- GUI interface
  - Players can interact with the plugin using an intuitive GUI interface
  - Players can easily see when the market opens using the interface
- Permissions
  - Admins can easily set who has permissions to make configurations to the plugin
- Configurable
  - Admins can easily configure the plugin to their liking
- Security:
    - The market is protected after hours, preventing players from accessing it from any means



### Permissions

markettime.setlocation: Allows the player to set the location of the market
markettime.access: Allows the player to access the Market afterhours

### Configuration

Market opening and closing time, using the server host's system time
```yaml
openTime: the time when the market opens (in 24 hour time)
closeTime: the time when the market closes (in 24 hour time)
radius: the radius of the market to protect after hours
```