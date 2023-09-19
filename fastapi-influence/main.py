from fastapi import FastAPI
from crawler import crawl_naver  # 크롤링 모듈 임포트

app = FastAPI()

@app.get("/")
def read_root():
    return {"message": "Hello, World!"}

@app.get("/crawl/")
def crawl_and_return():
    # 크롤링 함수 호출
    result = crawl_naver()
    return {"title": result}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)