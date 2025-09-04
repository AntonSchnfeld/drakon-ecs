# drakon-ecs

A high-performance **Entity Component System (ECS)** framework for Java.  
It is designed around:
- **Archetypes & Chunked SoA storage** for cache-friendly iteration
- **Versioned long entities** with live checks
- **Flexible component model** primitive component makeup and object component support
- **Event-driven system execution** with ordered event streams
- **Parallel scheduling** via dependency graph analysis

⚠️ **Status: Experimental (0.x.x)**  
APIs are not stable yet. Expect breaking changes between minor versions.

---

## Project Status

- [ ] Core entity management (IDs, versioning, alive checks)
- [ ] Archetypes & chunked SoA storage
- [ ] Deterministic system execution
- [ ] Event-driven systems
- [ ] Parallel dependency-graph scheduler
- [ ] Optimizations (primitive arrays, off-heap, cached queries)
- [ ] Tooling & debugging utilities
- [ ] Stable 1.0.0 release

---

## Contributing

This is intended as a **solo project** authored by me.  
I’m unlikely to accept pull requests, but:
- Discussions, design feedback, and benchmarking reports are very welcome.
- Issues may be used for bug tracking or design notes.

---

## License

MIT License. See [LICENSE](LICENSE) for details.
