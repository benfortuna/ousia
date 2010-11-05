package org.mnode.ousia

import ca.odell.glazedlists.GlazedLists;


def list = new OusiaBuilder().sortedList(GlazedLists.eventList([1, 4, 5, 2, 6, 8]), comparator: { a, b -> a <=> b} as Comparator<?>)

println list