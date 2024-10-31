package com.xiaozhi.zhh.aoaojiao.toolkit;

import org.w3c.dom.Node;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * 树工具类
 *
 * @author DD
 * date    2024/10/23 11:30
 */
public class TreeUtil {

    /**
     * 递归，将list合成树
     *
     * @param list           需要合成树的List
     * @param rootCheck      判断E中为根节点的条件，如：x->x.getPId()==-1L , x->x.getParentId()==null,x->x.getParentMenuId()==0
     * @param parentCheck    判断E中为父节点条件，如：(x,y)->x.getId().equals(y.getPId())
     * @param setSubChildren E中设置下级数据方法，如： Menu::setSubMenus
     * @param <E>            泛型实体对象
     * @return 合成好的树
     */
    public static <E> List<E> makeTree(List<E> list,
                                       Predicate<E> rootCheck,
                                       BiFunction<E, E, Boolean> parentCheck,
                                       BiConsumer<E, List<E>> setSubChildren) {
        return list.stream().filter(rootCheck)
                .peek(x -> setSubChildren.accept(x, makeChildren(x, list, parentCheck, setSubChildren)))
                .collect(Collectors.toList());
    }

    private static <E> List<E> makeChildren(E parent,
                                            List<E> allData,
                                            BiFunction<E, E, Boolean> parentCheck,
                                            BiConsumer<E, List<E>> children) {
        return allData.stream().filter(x -> parentCheck.apply(parent, x))
                .peek(x -> children.accept(x, makeChildren(x, allData, parentCheck, children)))
                .collect(Collectors.toList());
    }


    /**
     * 不进行递归，使用Map合成树
     *
     * @param menuList       需要合成树的List
     * @param pId            对象中的父ID字段,如:Menu:getPid
     * @param id             对象中的id字段 ,如：Menu:getId
     * @param rootCheck      判断E中为根节点的条件，如：x->x.getPId()==-1L , x->x.getParentId()==null,x->x.getParentMenuId()==0
     * @param setSubChildren E中设置下级数据方法，如： Menu::setSubMenus
     * @param <T>            ID字段类型
     * @param <E>            泛型实体对象
     * @return Tree
     */
    public static <T, E> List<E> makeTree(List<E> menuList,
                                          Function<E, T> pId,
                                          Function<E, T> id,
                                          Predicate<E> rootCheck,
                                          BiConsumer<E, List<E>> setSubChildren) {
        HashMap<T, List<E>> parentMenuMap = new HashMap<>();
        List<E> result = new ArrayList<>();

        // 构建父子映射表
        menuList.forEach(node -> parentMenuMap.computeIfAbsent(pId.apply(node),
                k -> new ArrayList<>()).add(node));

        // 构建树
        menuList.forEach(node -> {
            List<E> childrenList = parentMenuMap.get(id.apply(node));
            setSubChildren.accept(node, childrenList != null ? childrenList : new ArrayList<>());
            if (rootCheck.test(node)) {
                result.add(node);
            }
        });
        return result;
    }

    /**
     * 将树打平成tree
     *
     * @param tree           需要打平的树
     * @param getSubChildren 设置下级数据方法，如： Menu::getSubMenus,x->x.setSubMenus(null)
     * @param setSubChildren 将下级数据置空方法，如： x->x.setSubMenus(null)
     * @param <E>            泛型实体对象
     * @return 打平后的数据
     */
    public static <E> List<E> flat(List<E> tree, Function<E, List<E>> getSubChildren, Consumer<E> setSubChildren) {
        List<E> res = new ArrayList<>();
        forPostOrder(tree, item -> {
            setSubChildren.accept(item);
            res.add(item);
        }, getSubChildren);
        return res;
    }


    /**
     * 前序遍历
     *
     * @param tree           需要遍历的树
     * @param consumer       遍历后对单个元素的处理方法，如：x-> System.out.println(x)、 System.out::println打印元素
     * @param setSubChildren 设置下级数据方法，如： Menu::getSubMenus,x->x.setSubMenus(null)
     * @param <E>            泛型实体对象
     */
    public static <E> void forPreOrder(List<E> tree, Consumer<E> consumer, Function<E, List<E>> setSubChildren) {
        for (E l : tree) {
            consumer.accept(l);
            List<E> es = setSubChildren.apply(l);
            if (es != null && !es.isEmpty()) {
                forPreOrder(es, consumer, setSubChildren);
            }
        }
    }


    /**
     * 层序遍历
     *
     * @param tree           需要遍历的树
     * @param consumer       遍历后对单个元素的处理方法，如：x-> System.out.println(x)、 System.out::println打印元素
     * @param setSubChildren 设置下级数据方法，如： Menu::getSubMenus,x->x.setSubMenus(null)
     * @param <E>            泛型实体对象
     */
    public static <E> void forLevelOrder(List<E> tree, Consumer<E> consumer, Function<E, List<E>> setSubChildren) {
        Queue<E> queue = new LinkedList<>(tree);
        while (!queue.isEmpty()) {
            E item = queue.poll();
            consumer.accept(item);
            List<E> childList = setSubChildren.apply(item);
            if (childList != null && !childList.isEmpty()) {
                queue.addAll(childList);
            }
        }
    }

    /**
     * 后序遍历
     *
     * @param tree           需要遍历的树
     * @param consumer       遍历后对单个元素的处理方法，如：x-> System.out.println(x)、 System.out::println打印元素
     * @param setSubChildren 设置下级数据方法，如： Menu::getSubMenus,x->x.setSubMenus(null)
     * @param <E>            泛型实体对象
     */
    public static <E> void forPostOrder(List<E> tree, Consumer<E> consumer, Function<E, List<E>> setSubChildren) {
        for (E item : tree) {
            List<E> childList = setSubChildren.apply(item);
            if (childList != null && !childList.isEmpty()) {
                forPostOrder(childList, consumer, setSubChildren);
            }
            consumer.accept(item);
        }
    }

    /**
     * 对树所有子节点按comparator排序
     *
     * @param tree        需要排序的树
     * @param comparator  排序规则Comparator，如：Comparator.comparing(MenuVo::getRank)按Rank正序 ,(x,y)->y.getRank().compareTo(x.getRank())，按Rank倒序
     * @param getChildren 获取下级数sssss据方法，如：MenuVo::getSubMenus
     * @param <E>         泛型实体对象
     * @return 排序好的树
     */
    public static <E> List<E> sort(List<E> tree, Comparator<? super E> comparator, Function<E, List<E>> getChildren) {
        for (E item : tree) {
            List<E> childList = getChildren.apply(item);
            if (childList != null && !childList.isEmpty()) {
                sort(childList, comparator, getChildren);
            }
        }
        tree.sort(comparator);
        return tree;
    }

    /**
     * 树中过滤
     *
     * @param tree        需要过滤的树
     * @param predicate   过滤条件
     * @param getChildren 获取下级数据方法，如：MenuVo::getSubMenus
     * @param <E>         泛型实体对象
     * @return List<E> 过滤后的树
     */
    public static <E> List<E> filter(List<E> tree, Predicate<E> predicate, Function<E, List<E>> getChildren) {
        List<E> filtered = new ArrayList<>();
        for (E node : tree) {
            List<E> children = getChildren.apply(node);
            if (children != null && !children.isEmpty()) {
                List<E> filteredChildren = filter(children, predicate, getChildren);
                if (predicate.test(node)) {
                    filtered.add(node);
                    getChildren.apply(node).clear();
                    getChildren.apply(node).addAll(filteredChildren);
                }
            } else if (predicate.test(node)) {
                filtered.add(node);
            }
        }
        return filtered;
    }


    /**
     * 树中搜索
     *
     * @param tree           树列表
     * @param predicate      搜索条件
     * @param getSubChildren 获取下级数据方法，如：MenuVo::getSubMenus
     * @param <E>            泛型实体对象
     * @return 返回搜索到的节点及其父级到根节点
     */
    public static <E> List<E> search(List<E> tree,
                                     Predicate<E> predicate,
                                     Function<E, List<E>> getSubChildren) {
        Iterator<E> iterator = tree.iterator();
        while (iterator.hasNext()) {
            E node = iterator.next();
            List<E> childList = getSubChildren.apply(node);
            if (childList != null && !childList.isEmpty()) {
                search(childList, predicate, getSubChildren);
            }
            if (!predicate.test(node) && (childList == null || childList.isEmpty())) {
                iterator.remove();
            } else {
                getSubChildren.apply(node).clear();
                if (childList != null) {
                    getSubChildren.apply(node).addAll(childList);
                }
            }
        }
        return tree;
    }

}

