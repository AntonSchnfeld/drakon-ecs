# drakon-ecs Roadmap

The project is developed in **phases**, each one mapped to a `0.x.0` release.  
Breaking changes are expected until the first `1.0.0`.

---

## 0.1.0 — Core Entities
- Entity IDs as `int` with versioning
- Live checks
- Basic component storage (simple maps)
- Add/remove/get component support
- First working unit tests

## 0.2.0 — Archetypes & Chunked SoA
- Archetype-based entity grouping
- Chunked SoA storage layout
- Migration between archetypes on add/remove
- Fast queries via archetype matching

## 0.3.0 — Systems
- Systems with fixed-order execution
- Systems declare component interest
- Efficient iteration over matching entities

## 0.4.0 — Events
- Event bus with publish/subscribe
- Events processed in-order
- Systems can subscribe to event types
- Batch event processing per frame

## 0.5.0 — Multithreading
- Systems declare read/write component sets
- Dependency graph scheduler
- Topological sort to maximize parallelism
- Worker pool for execution phases

## 0.6.0 — Optimizations
- Primitive-array backend for numeric components
- Off-heap storage option for critical data
- Cached queries for static systems
- Reduced GC churn

## 0.7.0+ — Tooling & Extensibility
- Debug visualization for archetypes & systems
- Performance metrics
- API polish
- Documentation & tutorials

---

## 1.0.0 — Stable API
- Fully tested & benchmarked
- Stable API guarantee
- Published to Maven Central
