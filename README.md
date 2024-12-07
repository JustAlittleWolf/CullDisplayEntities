# Cull Display Entities

Implements visibility bounding boxes for Display Entities.

Minecraft uses [Frustum Culling](https://en.wikipedia.org/wiki/Viewing_frustum), which in simple terms hides things that are behind the camera.

However, by default, blockdisplayentities have this enabled, when the "width" and "height" tag are not set.
Because most servers don't set this property when using blockdisplayentities, this means that they won't get culled, when behind the camera. This
mod fixes that issue, by calculating the bounding box for each blockdisplayentity. This also has the effect of culling the entities when
using [Entity Culling](https://modrinth.com/mod/entityculling).

In some cases blockdisplayentities might be culled incorrectly, if the model displayed too large.