package goldmine.medium;

import java.util.*;

public class LargestTree {
    public static Integer largestTree(final Map<Integer,Integer> immediateParent){
        Integer maxTreeSize = 0;
        Integer minRootId= 0;
        final Map<Integer,List<Integer>> parentToChild = new HashMap<>();
        final List<Integer> rootIndexes = new ArrayList<>();
        for(Map.Entry<Integer,Integer> childToParent : immediateParent.entrySet()){
            Integer childIndex = childToParent.getKey();
            Integer parentIndex = childToParent.getValue();
            parentToChild.putIfAbsent(parentIndex,new ArrayList<>());
            parentToChild.get(parentIndex).add(childIndex);
            if(!immediateParent.containsKey(parentIndex)){
                rootIndexes.add(parentIndex);
            }
        }
        for(Integer rootIndex:rootIndexes){
            final Integer treeSize= getTreeSize(parentToChild,rootIndex);
            if(treeSize>maxTreeSize){
                maxTreeSize=treeSize;
                minRootId=rootIndex;
            }else if(treeSize == maxTreeSize){
                minRootId = Math.min(minRootId,rootIndex);
            }
        }
        return minRootId;
    }
    public static Integer getTreeSize(final Map<Integer,List<Integer>> parentToChild,final Integer rootIndex) {
        Integer result = 0;
        final Stack<Integer> nodes = new Stack<>();
        nodes.push(rootIndex);
        while (!nodes.empty()) {
            Integer index = nodes.pop();
            for (Integer childIndex : parentToChild.getOrDefault(index, new ArrayList<>())) {
                nodes.push(childIndex);
                result++;
            }
        }
        return result;
    }
}
