package org.example.list

class GroovyListExample {

    // way to create list
    static def create() {
        def list = [1, 2, 3]
        def emptyList = []
        def linkedList = [1, 2, 3] as LinkedList
        ArrayList arrayList = [1, 2, 3]
        def copyList = new ArrayList(arrayList)
        def cloneList = arrayList.clone()
        assert cloneList == arrayList
        assert cloneList == copyList        // true -- Groovy use the "==" operator to compare the elements in two lists for equality
    }

    // way to retrieve
    static def retrieve() {
        def list = ["hello", 'hi']
        assert list[1] == 'hi'
        assert list.get(1) == 'hi'
        assert list.getAt(1) == 'hi'
        assert list[-1] == 'hi'
        assert list.getAt(-2) == 'hello'
    }

    // way to add
    static def add() {
        def list = []

        list << 1
        list.add('Apple')
        assert list == [1, "Apple"]

        list[2] = 'Box'
        list[4] = true      // if the length of the list is less than the index specified, Groovy adds as many null values as the difference
        assert list == [1, 'Apple', "Box", null, true]

        def list2 = [1, 2]
        list += list2
        list += 12
        assert list == [1, 'Apple', "Box", null, true, 1, 2, 12]    // this operator creates a new list object and assigns it to the variable list
    }

    // way to update
    static def update() {
        def list = [1, "Apple", 80, 'App']
        list[1] = "Box"
        list.set(2, 90)
        assert list == [1, "Box", 90, "App"]

        list[1] = 100
        list.set(2, "A")
        assert list == [1, 100, "A", "App"]
    }

    // way to remove
    static def remove() {
        def list = [1, 2, 3, 4, 5, 5, 6, 6, 7]
        list.remove(3)
        assert list == [1, 2, 3, 5, 5, 6, 6, 7]

        list.removeElement(5)   // This removes the first occurrence of the element from the list
        assert list == [1, 2, 3, 5, 6, 6, 7]

        // we can use the minus operator to remove all occurrences of an element from the list
        // This operator, however, does not mutate the underlying list - it returns a new list
        assert list - 6 == [1, 2, 3, 5, 7]
        assert list - 1 - 2 - 3 - 5 - 6 == [7]
    }

    // way to filter
    static def filter() {
        def filterList = [2, 1, 3, 4, 5, 6, 76]

        // "it" is a keyword in closure
        assert filterList.find({it > 3}) == 4
        assert filterList.findAll({it > 3}) == [4, 5, 6, 76]
        assert filterList.findAll({it instanceof Number}) == [2, 1, 3, 4, 5, 6, 76]

        // the difference between grep and find methods is that grep can accept an Object or a Closure as an argument
        assert filterList.grep(Number) == [2, 1, 3, 4, 5, 6, 76]
        assert filterList.grep({it > 6}) == [76]

        def uniqueList = [1, 3, 3, 4]
        uniqueList.unique()
        assert uniqueList == [1, 3, 4]

        assert ['A', "B", "Ba", "Bat", 'Cat'].toUnique({it.size()}) == ['A', "Ba", "Bat"]

        // if we want to check that some or all items in a list satisfy a certain condition, we can use the every() and any() methods
        def conditionList = [2, 1, 3, 4, 5, 6, 76]
        assert !conditionList.every({it < 6})

        // the any() method, on the other hand, returns true if any element in the list satisfies the condition
        assert conditionList.any({it % 2 == 0})
    }

    // way to sort
    static def sort() {
        assert [1, 2, 1, 0].sort() == [0, 1, 1, 2]

        // Comparator with custom sorting logic
        Comparator comparator = {a, b -> a == b ? 0 : a < b ? 1 : -1}
        def list = [1, 2, 1, 0]
        list.sort(comparator)
        assert list == [2, 1, 1, 0]

        // max
        def strList = ["na", "ppp", "as"]
        assert strList.max() == "ppp"

        // min with custom comparator
        Comparator minComparator = {a, b -> a == b ? 0 : a < b ? -1 : 1}
        def numberList = [3, 2, 0, 7]
        assert numberList.min(minComparator) == 0
    }

    // way to collect
    static def collect() {
        def list = ["Kay", 'Henry', 'Justin', "Tom"]
        assert list.collect({"Hi " + it}) == ["Hi "]
    }

    static void main(String[] args) {
        create()
        retrieve()
        add()
        update()
        remove()
        filter()
        sort()
    }

}
