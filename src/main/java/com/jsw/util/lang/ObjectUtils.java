/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jsw.util.lang;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 对象操作工具类，继承 org.apache.commons.lang3.ObjectUtils 类
 * @author ThinkGem
 * @version 2020-3-29
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

	/**
	 * 转换为 Double 类型
	 */
	public static Double toDouble(final Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return NumberUtils.toDouble(StringUtils.trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为 Float 类型
	 */
	public static Float toFloat(final Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为 Long 类型
	 */
	public static Long toLong(final Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为 Integer 类型
	 */
	public static Integer toInteger(final Object val) {
		return toLong(val).intValue();
	}

	/**
	 * 转换为 Boolean 类型 'true', 'on', 'y', 't', 'yes' or '1'
	 *  (case insensitive) will return true. Otherwise, false is returned.
	 */
	public static Boolean toBoolean(final Object val) {
		if (val == null) {
			return false;
		}
		return BooleanUtils.toBoolean(val.toString()) || "1".equals(val.toString());
	}

	/**
	 * 转换为字符串
	 */
	public static String toString(final Object obj) {
		return toString(obj, StringUtils.EMPTY);
	}

	/**
	 * 转换为字符串，如果对象为空，则使用 defaultVal 值
	 */
	public static String toString(final Object obj, final String defaultVal) {
		return obj == null ? defaultVal : obj.toString();
	}

	/**
	 * 转换为字符串，忽略空值。如 null 字符串，则被认为空值，如下：
	 * 		"" to "" ; null to "" ; "null" to "" ; "NULL" to "" ; "Null" to ""
	 */
	public static String toStringIgnoreNull(final Object val) {
		return ObjectUtils.toStringIgnoreNull(val, StringUtils.EMPTY);
	}

	/**
	 * 转换为字符串，忽略空值。如 null 字符串，则被认为空值，如下：
	 * 		"" to defaultVal ; null to defaultVal ; "null" to defaultVal ; "NULL" to defaultVal ; "Null" to defaultVal
	 */
	public static String toStringIgnoreNull(final Object val, String defaultVal) {
		String str = ObjectUtils.toString(val);
		return !"".equals(str) && !"null".equals(str.trim().toLowerCase()) ? str : defaultVal;
	}

	/**
	 * 拷贝一个对象（但是子对象无法拷贝）
	 * @param source
	 * @param ignoreProperties
	 */
	public static Object copyBean(Object source, String... ignoreProperties){
		if (source == null){
			return null;
		}
		try {
			Object target = source.getClass().getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(source, target, ignoreProperties);
			return target;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw ExceptionUtils.unchecked(e);
		}
	}
	
	/**
	 * 克隆一个对象（完全拷贝）
	 * @param source
	 */
//	public static Object cloneBean(Object source){
//		if (source == null){
//			return null;
//		}
//    	byte[] bytes = ObjectUtils.serialize(source);
//    	Object target = ObjectUtils.unserialize(bytes);
//	    return target;
//	}
	
	/**
	 * 序列化对象
	 * @param object
	 * @return
	 */
//	public static byte[] serialize(Object object) {
//		return ObjectUtils.serializeFst(object);
//	}

	/**
	 * 反序列化对象
	 * @param bytes
	 * @return
	 */
//	public static Object unserialize(byte[] bytes) {
//		return ObjectUtils.unserializeFst(bytes);
//	}
	
	/**
	 * 序列化对象
	 * @param object
	 * @return
	 */
	public static byte[] serializeJava(Object object) {
		if (object == null){
			return null;
		}
		long beginTime = System.currentTimeMillis();
		byte[] bytes = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);) {
			oos.writeObject(object);
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long totalTime = System.currentTimeMillis() - beginTime;
		if (totalTime > 3000){
			System.out.println("Serialize time: " + TimeUtils.formatDateAgo(totalTime));
		}
		return bytes;
	}

	/**
	 * 反序列化对象
	 * @param bytes
	 * @return
	 */
	public static Object unserializeJava(byte[] bytes) {
		if (bytes == null){
			return null;
		}
		long beginTime = System.currentTimeMillis();
		Object object = null;
		if (bytes.length > 0) {
			try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
					ObjectInputStream ois = new ObjectInputStream(bais);) {
				object = ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		long totalTime = System.currentTimeMillis() - beginTime;
		if (totalTime > 3000){
			System.out.println("Unserialize time: " + TimeUtils.formatDateAgo(totalTime));
		}
		return object;
	}
	
	/*private static ThreadLocal<FSTConfiguration> fstConfiguration =
				new NamedThreadLocal<FSTConfiguration>("FSTConfiguration") {
		public FSTConfiguration initialValue() {
			return FSTConfiguration.createDefaultConfiguration();
		}
	};*/

	/**
	 * FST 序列化对象
	 * @param object
	 * @return
	 */
//	public static byte[] serializeFst(Object object) {
//		if (object == null){
//			return null;
//		}
//		long beginTime = System.currentTimeMillis();
//		byte[] bytes = fstConfiguration.get().asByteArray(object);
//		long totalTime = System.currentTimeMillis() - beginTime;
//		if (totalTime > 3000){
//			System.out.println("Fst serialize time: " + TimeUtils.formatDateAgo(totalTime));
//		}
//		return bytes;
//	}

	/**
	 * FST 反序列化对象
	 * @param bytes
	 * @return
	 */
//	public static Object unserializeFst(byte[] bytes) {
//		if (bytes == null){
//			return null;
//		}
//		long beginTime = System.currentTimeMillis();
//		Object object = fstConfiguration.get().asObject(bytes);
//		long totalTime = System.currentTimeMillis() - beginTime;
//		if (totalTime > 3000){
//			System.out.println("Fst unserialize time: " + TimeUtils.formatDateAgo(totalTime));
//		}
//		return object;
//	}

	/**
	 * 注解到对象复制，只复制能匹配上的方法。
	 * @param annotation
	 * @param object
	 */
	public static void annotationToObject(Object annotation, Object object){
		if (annotation != null){
			Class<?> annotationClass = annotation.getClass();
			Class<?> objectClass = object.getClass();
			for (Method m : objectClass.getMethods()){
				if (StringUtils.startsWith(m.getName(), "set")){
					try {
						String s = StringUtils.uncapitalize(StringUtils.substring(m.getName(), 3));
						Object obj = annotationClass.getMethod(s).invoke(annotation);
						if (obj != null && !"".equals(obj.toString())){
							if (object == null){
								object = objectClass.newInstance();
							}
							m.invoke(object, obj);
						}
					} catch (Exception e) {
						// 忽略所有设置失败方法
					}
				}
			}
		}
	}

//	private static Pool<Kryo> kryoPool = new Pool<Kryo>(true, false, 8) {
//		protected Kryo create() {
//			Kryo kryo = new Kryo();
//			kryo.setRegistrationRequired(false);
//			kryo.setReferences(false);
//			return kryo;
//		}
//	};
//	private static Pool<Output> outputPool = new Pool<Output>(true, false, 16) {
//		protected Output create() {
//			return new Output(1024, -1);
//		}
//	};
//	private static Pool<Input> inputPool = new Pool<Input>(true, false, 16) {
//		protected Input create() {
//			return new Input(1024);
//		}
//	};
//
//	/**
//	 * Kryo 序列化对象
//	 * @param object
//	 * @return
//	 */
//	public static byte[] serializeKryo(Object object) {
//		Kryo kryo = kryoPool.obtain();
//		Output output = outputPool.obtain();
//		try {
//			output.reset();
//			kryo.writeClassAndObject(output, object);
//			return output.toBytes();
//		} finally {
//			kryoPool.free(kryo);
//			outputPool.free(output);
//		}
//	}
//
//	/**
//	 * Kryo 反序列化对象
//	 * @param bytes
//	 * @return
//	 */
//	public static Object unserializeKryo(byte[] bytes) {
//		Kryo kryo = kryoPool.obtain();
//		Input input = inputPool.obtain();
//		try {
//			input.setBuffer(bytes);
//			return kryo.readClassAndObject(input);
//		} finally {
//			kryoPool.free(kryo);
//			inputPool.free(input);
//		}
//	}
	
}