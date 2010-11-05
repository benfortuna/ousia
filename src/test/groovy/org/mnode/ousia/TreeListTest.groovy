package org.mnode.ousia

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.TreeList;
import ca.odell.glazedlists.TreeList.Format;


def list = new OusiaBuilder().treeList(GlazedLists.eventList([[node: 'A', value: 1], [node: 'A', value: 4], [node: 'B', value: 5], [node: 'A', value: 2], [node: 'B', value: 6], [node: 'B', value: 8]]),
	 expansionModel: TreeList.NODES_START_EXPANDED, format: [
        allowsChildren: {element -> true},
        getComparator: {depth -> (depth == 1) ? {a, b -> a.node <=> b.node} as Comparator<?> : null},
        getPath: {path, element ->
			 println element
			 path << element.node
			 path << element
		 }
    ] as Format<?>)

println list