<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableBillingAddress extends AbstractMigration
{

    public function change(): void
    {
        $billAddress = $this->table('billing_address', ['signed' => false]);
        $billAddress->addColumn('user_id', 'biginteger', ['signed' => false])
            ->addColumn('country_code', 'string', ['limit' => 2])
            ->addColumn('city', 'string', ['limit' => 80])
            ->addColumn('region', 'string', ['limit' => 80])
            ->addColumn('full_name', 'string', ['limit' => 80])
            ->addColumn('address', 'string', ['limit' => 80])
            ->addColumn('street_no', 'string', ['limit' => 20])
            ->addColumn('post_code', 'string', ['limit' => 20])
            ->addForeignKey('user_id', 'users', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
