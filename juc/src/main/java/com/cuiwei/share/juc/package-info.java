package com.cuiwei.share.juc;


// 根据修改的数据类型，可以将JUC包中的原子操作类可以分为4类。
//
// 基本类型: AtomicInteger, AtomicLong, AtomicBoolean。
// 数组类型: AtomicIntegerArray, AtomicLongArray, AtomicReferenceArray。
// 引用类型: AtomicReference, AtomicStampedRerence, AtomicMarkableReference。
// 对象的属性修改类型: AtomicIntegerFieldUpdater, AtomicLongFieldUpdater, AtomicReferenceFieldUpdater。
// 1.8新加:DoubleAccumulator,DoubleAdder,LongAccumulator,LongAdder。
//
// 这些类存在的目的是对相应的数据进行原子操作。所谓原子操作，是指操作过程不会被中断，保证数据操作是以原子方式进行的