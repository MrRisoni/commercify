<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableProductReviews extends AbstractMigration
{

    public function change(): void
    {
        $productsReviews = $this->table('product_reviews', ['signed' => false]);
        $productsReviews->addColumn('product_ud', 'biginteger', ['signed' => false])
            ->addColumn('user_id', 'biginteger', ['signed' => false])
            ->addColumn('stars', 'decimal', ['precision' => 2,'scale'=>1])
            ->addColumn('comment', 'string', ['limit' => 255])
            ->addColumn('created', 'datetime')
            ->addForeignKey('product_ud', 'products', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('user_id', 'users', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
