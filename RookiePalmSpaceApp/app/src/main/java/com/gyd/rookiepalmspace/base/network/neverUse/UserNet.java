package com.gyd.rookiepalmspace.base.network.neverUse;

import com.gyd.rookiepalmspace.base.network.neverUse.AbstractNet;

import org.json.JSONException;


/**
 * 登陆、注册处理模块,与服务器进行交互
 * 
 * @author gyd
 * 
 *         2014-11-6
 */
@SuppressWarnings("rawtypes")
public class UserNet<T> extends AbstractNet {
/*
	*//**
	 * 检查登陆或注册信息
	 *
	 * @return 注册成功的用户信息或者null
	 * @throws Exception
	 *//*
	@SuppressWarnings("unchecked")
	public UserInfo checkLogin(Map<String, Object> params, String path)
			throws Exception {
		// return getInfo(params, path,UserInfo.class);
		InputStream is = contactToServiceByPost(params, path);
		if (is != null) {
			UserInfo data = parseUserInfo(is, new TypeToken<UserInfo>() {
			});
			return data;
		}
		return null;
	}

	*//**
	 * 获取用户收藏的书籍信息
	 *
	 * @param params
	 *            请求参数
	 * @param path
	 *            请求路径
	 * @return 书籍信息集合
	 * @throws Exception
	 *//*
	public List<Book> getMyCollectBookInfo(Map<String, Object> params,
			String path) throws Exception {
		@SuppressWarnings("unchecked")
		List<Book> books = getInfoList(params, path, new JsonParseListInter() {

			@Override
			public List<Book> parseToList(InputStream is) {
				Gson gson = new Gson();
				List<Book> books = gson.fromJson(new InputStreamReader(is),
						new TypeToken<List<Book>>() {
						}.getType());
				return books;
			}
		});
		return books;
	}

	*//**
	 * 解析用户信息组成的json字符串为一个用户信息实体对象
	 *
	 * @param is
	 *            包含用户信息的输入流
	 * @param typeToken
	 *            类型
	 * @return
	 *//*
	private UserInfo parseUserInfo(InputStream is, TypeToken<UserInfo> typeToken) {
		Gson gson = new Gson();
		UserInfo data = gson.fromJson(new InputStreamReader(is),
				typeToken.getType());
		return data;
	}



	*//**
	 * 获得用户购物车中的书籍信息
	 *
	 * @param taskParams
	 *            请求参数
	 * @param path
	 *            请求路径
	 * @return
	 * @throws Exception
	 *//*
	public List<ShoppingCartItem> getBookFromCart(
			Map<String, Object> taskParams, String path) throws Exception {
		@SuppressWarnings("unchecked")
		List<ShoppingCartItem> items = getInfoList(taskParams, path,
				new JsonParseListInter() {

					@Override
					public List<ShoppingCartItem> parseToList(InputStream is) {
						Gson gson = new Gson();
						List<ShoppingCartItem> items = gson.fromJson(
								new InputStreamReader(is),
								new TypeToken<List<ShoppingCartItem>>() {
								}.getType());
						return items;
					}
				});
		return items;
	}


	*//**
	 * 得到用户的所有历史订单信息
	 *
	 * @param taskParams
	 * @param path
	 * @return
	 * @throws Exception
	 *//*
	public List<BookSaleOrder> getMyOrders(Map<String, Object> taskParams,
			String path) throws Exception {
		@SuppressWarnings("unchecked")
		List<BookSaleOrder> items = getInfoList(taskParams, path,
				new JsonParseListInter() {

					@Override
					public List<BookSaleOrder> parseToList(InputStream is) {
						Gson gson = new Gson();
						List<BookSaleOrder> items = gson.fromJson(
								new InputStreamReader(is),
								new TypeToken<List<BookSaleOrder>>() {
								}.getType());
						return items;
					}
				});
		return items;
	}

	*//**
	 * 得到某个订单的订单子项的信息
	 *
	 * @param taskParams
	 * @param path
	 * @return
	 * @throws Exception
	 *//*
	public Map<String, Object> getMyOrderDtAndBookInfos(
			Map<String, Object> taskParams, String path) throws Exception {
		@SuppressWarnings("unchecked")

		InputStream is = contactToServiceByPost(taskParams, path);
		if (is != null) {
			//得到返回的结果json对象
			String resultJson = Tools.parseStringJson(is);
			//解析json字符串,封装成实体数据返回到前台
			Map<String,Object> items = jsonToBean(resultJson);
			return items;
		}
		return null;
	}

	*//**
	 * 将json字符串解析为实体数据集合
	 * @param resultJson
	 * @return
	 * @throws JSONException
	 *//*
	private Map<String, Object> jsonToBean(String resultJson) throws JSONException {
		//存放解析出来的订单子项信息集合以及对应的书籍信息集合
		Map<String,Object> map = new HashMap<String, Object>();
		//解析出来的订单子项信息集合
		List<BookSaleOrderDetail> bookSaleOrderDetails = new ArrayList<BookSaleOrderDetail>();
		//解析出来的书籍信息集合
		List<Book> books = new ArrayList<Book>();

		JSONObject jsonObject = new JSONObject(resultJson);
		//得到json中代表订单子项集合的数组
		JSONArray orderDtsArray = (JSONArray) jsonObject.get(Constants.ORDER_DT);
		//解析订单子项数组为实体数据对象
		for(int i = 0; i < orderDtsArray.length(); i++){
			JSONObject jsonObject2 = (JSONObject) orderDtsArray.get(i);
			int orderId = jsonObject2.getInt(BookSaleOrderDetail.ORDER_ID);//订单编号
			int orderDtId = jsonObject2.getInt(BookSaleOrderDetail.ORDER_DT_ID);//订单子项编号
			int bookId = jsonObject2.getInt(BookSaleOrderDetail.BOOK_ID);//书籍编号
			int num = jsonObject2.getInt(BookSaleOrderDetail.NUM);//书籍数量
			BookSaleOrderDetail bookSaleOrderDetail = new BookSaleOrderDetail(orderId, orderDtId, bookId, num);
			bookSaleOrderDetails.add(bookSaleOrderDetail);
		}

		JSONArray booksArray = (JSONArray) jsonObject.get(Constants.BOOKS);
		for(int i=0;i<booksArray.length();i++){
			JSONObject jsonObject2 = (JSONObject) booksArray.get(i);
			int bookId = jsonObject2.getInt(Book.BOOK_ID);
			String bookName = jsonObject2.getString(Book.BOOK_NAME);
			double unitPrice = jsonObject2.getDouble(Book.UNIT_PRICE);
			int stock = jsonObject2.getInt(Book.STOCK);
			int salesCount = jsonObject2.getInt(Book.SALES_COUNT);
			double score = jsonObject2.getDouble(Book.SCORE);
			String bookSmallImgPath = jsonObject2.getString(Book.BOOK_SMALL_IMG_PATH);
			String bookMiddleImgPath = jsonObject2.getString(Book.BOOK_MIDDLE_IMG_PATH);
			String bookLargeImgPath = jsonObject2.getString(Book.BOOK_LARGE_IMG_PATH);
			String briefIntroduce = jsonObject2.getString(Book.BRIEF_INTRODUCE);
			int categoryId = jsonObject2.getInt(Book.CATEGORY_ID);

			Book book = new Book(bookId, bookName, unitPrice, stock, salesCount, score, bookSmallImgPath, bookMiddleImgPath, bookLargeImgPath, briefIntroduce, categoryId);
			books.add(book);

		}
		map.put(Constants.BOOKS, books);
		map.put(Constants.ORDER_DT, bookSaleOrderDetails);
		return map;
	}

	*//**
	 * 获取用户的历史地址信息集合
	 * @param taskParams
	 * @param path
	 * @return
	 * @throws Exception
	 *//*
	@SuppressWarnings("unchecked")
	public List<AddressInfo> getMyAddressList(Map<String, Object> taskParams,
			String path) throws Exception {
		List<AddressInfo> items = getInfoList(taskParams, path,
				new JsonParseListInter() {

					@Override
					public List<AddressInfo> parseToList(InputStream is) {
						Gson gson = new Gson();
						List<AddressInfo> items = gson.fromJson(
								new InputStreamReader(is),
								new TypeToken<List<AddressInfo>>() {
								}.getType());
						return items;
					}
				});
		return items;
	}

	*//**
	 * 确认收货的操作
	 * @param taskParams
	 * @param path
	 * @return
	 * @throws Exception
	 *//*
	public String receiveOrder(Map<String, Object> taskParams,
			String path) throws Exception {
		return getStringResultFromServer(taskParams, path);
	}

	*//**
	 * 新增地址
	 * @param taskParams
	 * @param path
	 * @return
	 * @throws Exception
	 *//*
	public String addNewAddress(Map<String, Object> taskParams,
			String path) throws Exception {
		return getStringResultFromServer(taskParams, path);
	}

	*//**
	 * 发送短信验证码
	 * @param taskParams
	 * @param path
	 * @return
	 * @throws Exception
	 *//*
	public String sendSms(Map<String, Object> taskParams, String path) throws Exception {
		return getStringResultFromServer(taskParams, path);
	}

	*//**
	 * 更新我的购物车 增加书籍数量或者减少书籍数量 或者移除书籍
	 *
	 * @param taskParams
	 * @param path
	 * @return
	 * @throws Exception
	 *//*
	public String updateMyCart(Map<String, Object> taskParams, String path)
			throws Exception {
		return getStringResultFromServer(taskParams, path);
	}

	*//**
	 * 处理下单的任务
	 *
	 * @param taskParams
	 *            请求参数
	 * @param path
	 *            请求路径
	 * @return
	 * @throws Exception
	 *//*
	public String makeOrder(Map<String, Object> taskParams, String path)
			throws Exception {
		return getStringResultFromServer(taskParams, path);
	}


	*//**
	 * 收藏书籍或取消收藏书籍的请求参数
	 *
	 * @param taskParams
	 * @param path
	 *            请求路径
	 * @return 结果 true 或 false
	 * @throws Exception
	 *//*
	public String collectBook(Map<String, Object> taskParams, String path)
			throws Exception {
		return getStringResultFromServer(taskParams, path);
	}


	*//**
	 * 修改密码
	 * @param taskParams
	 * @param path
	 * @return
	 * @throws Exception
	 *//*
	public String modifyPwd(Map<String, Object> taskParams, String path) throws Exception {
		return getStringResultFromServer(taskParams, path);
	}

	*//**
	 * 用户注册
	 * @param taskParams
	 * @param path
	 * @return
	 * @throws Exception
	 *//*
	public String register(Map<String, Object> taskParams, String path) throws Exception {
		return getStringResultFromServer(taskParams, path);
	}

	private String getStringResultFromServer(Map<String, Object> taskParams, String path) throws Exception{
		@SuppressWarnings("unchecked")
		InputStream is = contactToServiceByPost(taskParams, path);
		if (is != null) {
			String result = Tools.parseStringJson(is);
			return result;
		}
		return null;
	}

	*/

}
