import requests
import json
import pymysql
import time
from bs4 import BeautifulSoup
N = 1;

def get_page(url):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 OPR/66.0.3515.115'}
    response = requests.get(url,headers = headers)
    return response;




def insert_mysql(title, img, author, price, cgy, intro,conn):
    sql = f"INSERT INTO bookinfo (title,author,categories,img,price,aid,intro) VALUES ('{title}','{author}','{cgy}','{img}',{price},0,'{intro}')"
    cur = conn.cursor()  # 获取游标
    cur.execute(sql)
    cur.close()
    conn.commit()
    global N
    print(f"第{N}条提交到数据库成功!")
    N = N + 1



def parse_data(text,conn):
    soup = BeautifulSoup(text, 'lxml')
    # print(soup)
    a_list = soup.find_all(name = 'ul',class_ = 'bang_list clearfix bang_list_mode' )
    for li in a_list:
        for x in range(0,19):
            try:
                # 书名
                title = li.find_all(name = 'li')[x].find(name='div',class_='name').text
                # 封面
                img = li.find_all(name = 'li')[x].find(name='div',class_='pic').img.attrs.get('src')
                # 作者
                author = li.find_all(name='li')[x].find(name='div', class_='publisher_info').find(name='a').text
                # 价格
                price = li.find_all(name='li')[x].find(name='div', class_='price').find(name='span',class_='price_r').text.replace('¥','')
                # 详情页
                d_url = li.find_all(name='li')[x].find(name='div',class_='pic').a.attrs.get('href')
                # 分类标签
                soup_d = BeautifulSoup(get_page(d_url).text,'lxml')
                cgy = soup_d.find(name = 'div', id = 'breadcrumb').find_all(name = 'a')[1].text
                # 简介
                intro = soup_d.find(name='span', class_ = 'head_title_name').text
                # print(title)
                print(img)
                # print(author)
                # print(price)
                insert_mysql(title,img,author,price,cgy,intro,conn)
            except Exception:
                pass




if __name__ == '__main__':
    conn = pymysql.connect(host="0.0.0.0", user="root", password="12345", database="BookStore", charset="utf8")
    print(conn)
    print(type(conn))
    # urls = {
    #     '童书':'http://bang.dangdang.com/books/bestsellers/01.41.00.00.00.00-recent30-0-0-1-',
    #     '成功/励志':'http://bang.dangdang.com/books/bestsellers/01.21.00.00.00.00-recent30-0-0-1-',
    #     '外语':'http://bang.dangdang.com/books/bestsellers/01.45.00.00.00.00-recent30-0-0-1-',
    #     '考试':'http://bang.dangdang.com/books/bestsellers/01.47.00.00.00.00-recent30-0-0-1-',
    #     '管理':'http://bang.dangdang.com/books/bestsellers/01.22.00.00.00.00-recent30-0-0-1-',
    #     '历史':'http://bang.dangdang.com/books/bestsellers/01.36.00.00.00.00-recent30-0-0-1-',
    #     '青春文学':'http://bang.dangdang.com/books/bestsellers/01.01.00.00.00.00-recent30-0-0-1-'
    # }
    urls = {

        'http://bang.dangdang.com/books/bestsellers/01.54.00.00.00.00-recent30-0-0-1-',
        'http://bang.dangdang.com/books/bestsellers/01.32.00.00.00.00-recent30-0-0-1-'
    }
    for x in urls:
        print(x)
        for line in range(1,8):
            # 1.发送请求
            # 往接口发送请求获取响应数据
            response = get_page(x+f'{line}')
            data = response.text
            # 2.解析数据
            parse_data(data,conn)
            time.sleep(20)
    conn.close()
