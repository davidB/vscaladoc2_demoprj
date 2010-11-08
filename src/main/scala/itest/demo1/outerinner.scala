package itest.demo1

object OuterObject {
    type InnerType = String
    object InnerObject
    class InnerClass
    trait InnerTrait
}

class OuterClass {
    type InnerPType[T] = List[T]
    object InnerObject
    class InnerClass
    trait InnerTrait
}