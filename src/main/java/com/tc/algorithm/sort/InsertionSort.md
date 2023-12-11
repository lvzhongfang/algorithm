对于少量元素的排序，插入排序是一个有效的算法。插入排序的工作方式像许多人排序一手扑克牌。
开始的时候手里没有牌，桌面上有所有牌。然后我们每次从桌面拿走一张牌并将它插入到我们手中
正确的位置。为了找到正确的位置，我们从右向左将这张牌与手里的牌进行比较。

![](../../../../../resources/insertionSort/insertionSort_001.png)

![](../../../../../resources/insertionSort/insertionSort_002.png)

````
public static void insertionSort (int [] array) {

    if (array == null || array.length <= 1) {
        return;
    }

    for (int j = 1; j < array.length; j++) { //run n times
        int key = array[j]; //run n - 1 times
        int i = j - 1; //run n - 1 times

        while (i >= 0 && array [i] > key) { //tj表示对于j本行执行的次数，$\sum_{j = 2}^n{x_tj}$,即对tj从2到n求和
            array [i + 1] = array[i]; //对tj - 1从2到n求和
            i = i - 1; //对tj - 1从2到n求和
        }

        array[i + 1] = key; //run n - 1 times
    }
}
````

插入排序最好到情况是数组已有序，此时时间复杂的为0n,最坏情况是数组逆序，此时必须将A[j]与A[1...j - 1]
比较，此时时间复杂度为On2
