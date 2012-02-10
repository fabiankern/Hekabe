package com.hekabe.dashboard.shared.parameter;

public interface YamlParameters {
	public static final String PARTITIONER = "partitioner";
	public static final String HINTED_HANDOFF = "hinted_handoff_enabled";
	public static final String MAX_WINDOW_TIME = "max_hint_window_in_ms";
	public static final String THROTTLE_DELAY = "hinted_handoff_throttle_delay_in_ms";
	public static final String SYNC_TYPE = "commitlog_sync";
	public static final String TIME_WINDOW_BATCH = "commitlog_sync_batch_window_in_ms";
	public static final String TIME_WINDOW_PERIODIC = "commitlog_sync_period_in_ms";
	public static final String COMMITLOG_TOTAL_SPACE = "commitlog_total_space_in_mb";
	public static final String REDUCE_CACHE_AT = "reduce_cache_sizes_at";
	public static final String REDUCE_CACHE_CAPACITY_TO = "reduce_cache_capacity_to";
	public static final String CONCURRENT_READS = "concurrent_reads";
	public static final String CONCURRENT_WRITES = "concurrent_writes";
	public static final String MEMTABLE_TOTAL_SPACE = "memtable_total_space_in_mb";
	public static final String MEMTABLE_WRITER_THREADS = "memtable_flush_writers";
	public static final String FLUSH_FRACTION = "flush_largest_memtables_at";
}
